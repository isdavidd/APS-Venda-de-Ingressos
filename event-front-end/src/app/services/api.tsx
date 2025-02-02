export async function fetchFromAPI<T>(
    endpoint: string,
    options?: RequestInit,
): Promise<T> {
    const response = await fetch(
        `http://localhost:8080/api/event-management${endpoint}`,
        {
            ...options,
            headers: {
                mode: 'no-cors',
                'Content-Type': 'application/json',
                ...(options?.headers || {}),
            },
        },
    );

    if (!response.ok) {
        throw new Error(`Erro: ${response.status} - ${response.statusText}`);
    }

    return response.json();
}
