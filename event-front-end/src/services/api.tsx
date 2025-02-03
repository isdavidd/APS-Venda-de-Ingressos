export const base_url = 'http://localhost:8080/event-management';
export async function fetchFromAPI<T>(
    endpoint: string,
    options?: RequestInit,
): Promise<T> {
    const headers = new Headers();
    headers.set('Content-Type', 'text/json');
    headers.set('Authorization', 'Basic ' + 'MTk2MDcxMTI3MDk6dGVzdGUxMjM=');

    const response = await fetch(`${base_url}${endpoint}`, {
        ...options,
        headers: headers,
    });

    if (!response.ok) {
        throw new Error(`Erro: ${response.status} - ${response.statusText}`);
    }

    return response.json();
}
