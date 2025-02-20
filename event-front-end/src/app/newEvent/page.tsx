'use client';
import { base_url } from '@/src/services/api';
import { useForm } from 'react-hook-form';
import { useRouter } from 'next/navigation';
import { useStore } from '../store/useStore';

interface EventFormData {
    eventName: string;
    ticketPrice: number;
    location: string;
    description: string;
    uf: string;
    date: string;
    hora: string;
    maxTickets: number;
}

const NewEvent = () => {
    const headers = new Headers();
    const { userData } = useStore();

    const router = useRouter();
    headers.set('Content-Type', 'application/json');
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<EventFormData>();

    const onSubmit = async (data: EventFormData) => {
        const payload = {
            nomeEvento: data.eventName,
            dataEvento: `${data.date}T${data.hora}`,
            estadoOrUFEvento: data.uf,
            localEvento: data.location,
            valorIngressoEvento: data.ticketPrice,
            capacidadeEvento: data.maxTickets,
            descricaoEvento: data.description,
        };

        try {
            const authToken = userData?.token ?? '';
            headers.set('Authorization', authToken);
        } catch (error) {
            console.error('Erro ao buscar token de autenticação:', error);
            throw error;
        }

        try {
            const response = await fetch(`${base_url}/create-event`, {
                method: 'POST',
                headers: headers,
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error('Falha na requisição');
            }

            window.alert('Evento criado com sucesso!');

            router.push('/');
        } catch (error) {
            console.error('Erro ao enviar o formulário:', error);
        }
    };

    const validateDate = (value: string) => {
        const selectedDate = new Date(value);
        const currentDate = new Date();
        const diffTime = selectedDate.getTime() - currentDate.getTime();
        const diffDays = diffTime / (1000 * 3600 * 24);
        return (
            diffDays >= 7 ||
            'A data do evento deve ser pelo menos 7 dias no futuro'
        );
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
            <div className="w-full max-w-md p-8 bg-white rounded-lg shadow-md">
                <h1 className="text-2xl font-bold text-center mb-6">
                    Criar Evento
                </h1>
                <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
                    <div>
                        <label
                            htmlFor="eventName"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Nome do Evento
                        </label>
                        <input
                            id="eventName"
                            type="text"
                            {...register('eventName', {
                                required: 'Nome do evento é obrigatório',
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.eventName && (
                            <span className="text-red-500 text-sm">
                                {errors.eventName.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="ticketPrice"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Valor do Ingresso
                        </label>
                        <input
                            id="ticketPrice"
                            type="number"
                            min="0"
                            {...register('ticketPrice', {
                                required: 'Valor do ingresso é obrigatório',
                                pattern: {
                                    value: /^(?:0|[1-9]\d*)(\.\d{1,2})?$/,
                                    message:
                                        'Digite um valor válido (ex: 10.50)',
                                },
                                valueAsNumber: true,
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.ticketPrice && (
                            <span className="text-red-500 text-sm">
                                {errors.ticketPrice.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="location"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Local
                        </label>
                        <input
                            id="location"
                            type="text"
                            {...register('location', {
                                required: 'Local é obrigatório',
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.location && (
                            <span className="text-red-500 text-sm">
                                {errors.location.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="description"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Descrição
                        </label>
                        <textarea
                            id="description"
                            {...register('description')}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.description && (
                            <span className="text-red-500 text-sm">
                                {errors.description.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="uf"
                            className="block text-sm font-medium text-gray-700"
                        >
                            UF ou Estado
                        </label>
                        <input
                            id="uf"
                            type="text"
                            {...register('uf', {
                                required: 'UF ou Estado é obrigatório',
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.uf && (
                            <span className="text-red-500 text-sm">
                                {errors.uf.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="date"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Data
                        </label>
                        <input
                            id="date"
                            type="date"
                            {...register('date', {
                                required: 'Data é obrigatória',
                                validate: validateDate,
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.date && (
                            <span className="text-red-500 text-sm">
                                {errors.date.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="hora"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Hora
                        </label>
                        <input
                            id="hora"
                            type="time"
                            {...register('hora', {
                                required: 'Hora é obrigatória',
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.hora && (
                            <span className="text-red-500 text-sm">
                                {errors.hora.message}
                            </span>
                        )}
                    </div>

                    <div>
                        <label
                            htmlFor="maxTickets"
                            className="block text-sm font-medium text-gray-700"
                        >
                            Capacidade de Ingressos
                        </label>
                        <input
                            id="maxTickets"
                            type="number"
                            {...register('maxTickets', {
                                required:
                                    'Capacidade de ingressos é obrigatória',
                                min: {
                                    value: 1,
                                    message: 'O número mínimo de ingressos é 1',
                                },
                                valueAsNumber: true,
                            })}
                            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        />
                        {errors.maxTickets && (
                            <span className="text-red-500 text-sm">
                                {errors.maxTickets.message}
                            </span>
                        )}
                    </div>

                    <div className="mt-6">
                        <button
                            type="submit"
                            className="w-full py-2 px-4 bg-[#212231] text-white font-bold rounded-md hover:bg-[#1f2132] focus:ring-4 focus:ring-blue-200"
                        >
                            Criar Evento
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default NewEvent;
