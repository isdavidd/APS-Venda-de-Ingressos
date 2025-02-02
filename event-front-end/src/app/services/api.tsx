export async function fetchFromAPI<T>(
    endpoint: string,
    options?: RequestInit,
): Promise<T> {
    const headers = new Headers();
    headers.set('Content-Type', 'text/json');
    headers.set('Authorization', 'Basic ' + 'YWRtaW46dGVzdGUxMjM');

    const response = await fetch(
        `http://localhost:8080/api/event-management${endpoint}`,
        {
            ...options,
            headers: headers,
        },
    );

    if (!response.ok) {
        throw new Error(`Erro: ${response.status} - ${response.statusText}`);
    }

    return response.json();
}
