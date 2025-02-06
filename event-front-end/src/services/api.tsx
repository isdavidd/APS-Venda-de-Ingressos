export const base_url = 'http://localhost:8080/event-management';
export async function fetchFromAPI<T>(
    endpoint: string,
    options?: RequestInit,
): Promise<T> {
    const headers = new Headers();
    headers.set('Content-Type', 'application/json');
    const actualState = JSON.parse(
        localStorage.getItem('zustandState') || '{}',
    );

    try {
        const authToken = actualState?.userData?.token;
        headers.set('Authorization', authToken);
    } catch (error) {
        throw new Error('Erro ao obter token de autenticação.');
    }

    const response = await fetch(`${base_url}${endpoint}`, {
        ...options,
        headers: headers,
    });

    if (!response.ok) {
        throw new Error(`Erro: ${response.status} - ${response.statusText}`);
    }

    return response.json();
}

export async function getAuthToken(): Promise<string> {
    const actualState = JSON.parse(
        localStorage.getItem('zustandState') || '{}',
    );

    if (!actualState?.userData?.token) {
        // `senhaOriginal` deve ser salva em texto puro no localStorage no momento do login/cadastro
        throw new Error('Credenciais não encontradas no localStorage');
    }

    const payload = {
        cpf: actualState?.userData?.cpf,
        senha: actualState?.userData?.senhaOriginal, // Senha deve estar em texto puro, NÃO a hash criptografada
    };

    try {
        const response = await fetch('http://localhost:8084/auth', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload),
        });

        if (!response.ok) {
            throw new Error('Falha ao obter token de autenticação');
        }
        const responseJson = await response.json();

        return responseJson.token; // Formata no padrão "Basic base64"
    } catch (error) {
        console.error('Erro ao buscar token de autenticação:', error);
        throw error;
    }
}
