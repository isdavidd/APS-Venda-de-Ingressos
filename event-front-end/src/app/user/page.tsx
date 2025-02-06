'use client';
import { useRouter } from 'next/navigation';
import { useState } from 'react';
import { useStore } from '../store/useStore';

export default function AuthPage() {
    const router = useRouter();
    const { update } = useStore();
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        nome: '',
        email: '',
        telefone: '',
        cpf: '',
        senha: '',
    });
    const [emailError, setEmailError] = useState('');
    const [nomeError, setNomeError] = useState('');
    const [telefoneError, setTelefoneError] = useState('');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;

        let formattedValue = value;

        if (name === 'telefone') {
            formattedValue = formatPhone(value);
            setTelefoneError(
                formattedValue.replace(/\D/g, '').length === 11 ||
                    formattedValue === ''
                    ? ''
                    : 'Telefone inválido',
            );
        } else if (name === 'cpf') {
            formattedValue = formatCPF(value);
        } else if (name === 'nome') {
            formattedValue = formatNome(value);
        }

        setFormData({ ...formData, [name]: formattedValue });
    };

    const formatPhone = (value: string) => {
        value = value.replace(/\D/g, ''); // Remove não numéricos

        if (value.length > 11) value = value.slice(0, 11);

        if (value.length === 0) return '';

        if (value.length <= 2) return value;
        if (value.length <= 7)
            return `(${value.slice(0, 2)}) ${value.slice(2)}`;
        return `(${value.slice(0, 2)}) ${value.slice(2, 7)}-${value.slice(7)}`;
    };

    const formatCPF = (value: string) => {
        value = value.replace(/\D/g, '');
        if (value.length > 11) value = value.slice(0, 11);
        if (value.length <= 3) return value;
        if (value.length <= 6) return `${value.slice(0, 3)}.${value.slice(3)}`;
        if (value.length <= 9)
            return `${value.slice(0, 3)}.${value.slice(3, 6)}.${value.slice(
                6,
            )}`;
        return `${value.slice(0, 3)}.${value.slice(3, 6)}.${value.slice(
            6,
            9,
        )}-${value.slice(9)}`;
    };

    const formatNome = (value: string) => {
        const regex = /^[a-zA-ZÀ-ÿ\s'-]*$/;
        if (!regex.test(value)) {
            setNomeError(
                'Nome inválido. Apenas letras, espaços, apóstrofos e hífens são permitidos.',
            );
            return formData.nome;
        }
        setNomeError('');
        return value;
    };

    const validateEmail = (email: string) => {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!isLogin && !validateEmail(formData.email)) {
            setEmailError('Por favor, insira um endereço de email válido.');
            return;
        }
        setEmailError('');
        console.log(isLogin ? 'Logando...' : 'Cadastrando...', formData);
    };

    const handleRegister = async (e: React.FormEvent) => {
        e.preventDefault();

        if (!isLogin) {
            if (!validateEmail(formData.email)) {
                setEmailError('Por favor, insira um endereço de email válido.');
                return;
            }

            if (formData.telefone.replace(/\D/g, '').length !== 11) {
                setTelefoneError('Telefone inválido');
                return;
            }

            if (nomeError) {
                return;
            }
        }

        setEmailError('');
        setTelefoneError('');
        const url = isLogin
            ? 'http://localhost:8084/auth'
            : 'http://localhost:8084/user-service/register';
        const payload = isLogin
            ? { cpf: formData.cpf.replace(/\D/g, ''), senha: formData.senha }
            : {
                  ...formData,
                  telefone: formData.telefone.replace(/\D/g, ''),
                  cpf: formData.cpf.replace(/\D/g, ''),
              };

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error(`Erro ao ${isLogin ? 'logar' : 'cadastrar'}`);
            }

            const data = await response.json();
            console.log('Resposta do servidor:', data);

            const userData = {
                nome: data?.nome,
                email: data?.email,
                role: data?.role,
                token: data?.token,
                senhaOriginal: formData?.senha,
            };

            update('userData', userData);

            // Aqui você pode redirecionar o usuário ou mostrar uma mensagem de sucesso
            if (isLogin) {
                router.push('/');
                console.log('Login bem-sucedido!');
                // Redirecionar para a página de dashboard ou home
            } else {
                console.log('Cadastro bem-sucedido!');
                setIsLogin(true); // Alternar para a tela de login após o cadastro
            }
        } catch (error) {
            console.error('Erro:', error);
            // Aqui você pode mostrar uma mensagem de erro para o usuário
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
            <div className="bg-white p-8 rounded-2xl shadow-md w-full max-w-md">
                <h2 className="text-center text-2xl font-semibold mb-6">
                    {isLogin ? 'Login' : 'Cadastro'}
                </h2>
                <form onSubmit={handleSubmit} className="space-y-4">
                    {!isLogin && (
                        <>
                            <input
                                type="text"
                                name="nome"
                                placeholder="Nome Completo"
                                value={formData.nome}
                                onChange={handleChange}
                                className={`w-full p-2 border rounded-lg ${
                                    nomeError ? 'border-red-500' : ''
                                }`}
                                required
                            />
                            {nomeError && (
                                <p className="text-red-500 text-sm">
                                    {nomeError}
                                </p>
                            )}
                            <input
                                type="text"
                                name="telefone"
                                placeholder="DDD + Telefone"
                                value={formData.telefone}
                                onChange={handleChange}
                                className={`w-full p-2 border rounded-lg ${
                                    telefoneError ? 'border-red-500' : ''
                                }`}
                            />
                            {telefoneError && (
                                <p className="text-red-500 text-sm">
                                    {telefoneError}
                                </p>
                            )}
                            <input
                                type="text"
                                name="cpf"
                                placeholder="CPF"
                                value={formData.cpf}
                                onChange={handleChange}
                                className="w-full p-2 border rounded-lg"
                                required
                            />
                            <input
                                type="email"
                                name="email"
                                placeholder="E-mail"
                                value={formData.email}
                                onChange={handleChange}
                                className={`w-full p-2 border rounded-lg ${
                                    emailError ? 'border-red-500' : ''
                                }`}
                                required
                            />
                            {emailError && (
                                <p className="text-red-500 text-sm">
                                    {emailError}
                                </p>
                            )}
                        </>
                    )}
                    {isLogin && (
                        <input
                            type="text"
                            name="cpf"
                            placeholder="CPF"
                            value={formData.cpf}
                            onChange={handleChange}
                            className="w-full p-2 border rounded-lg"
                            required
                        />
                    )}
                    <input
                        type="password"
                        name="senha"
                        placeholder="Senha"
                        value={formData.senha}
                        onChange={handleChange}
                        className="w-full p-2 border rounded-lg"
                        required
                    />
                    <button
                        type="submit"
                        className="w-full bg-[#212231] text-white py-2 rounded-lg hover:bg-gray-900 transition"
                        onClick={handleRegister}
                    >
                        {isLogin ? 'Entrar' : 'Cadastrar'}
                    </button>
                </form>
                <p className="text-center mt-4 text-gray-600">
                    {isLogin ? 'Não tem uma conta?' : 'Já tem uma conta?'}{' '}
                    <button
                        type="button"
                        onClick={() => setIsLogin(!isLogin)}
                        className="text-[#505381] font-semibold"
                    >
                        {isLogin ? 'Cadastre-se' : 'Faça login'}
                    </button>
                </p>
            </div>
        </div>
    );
}
