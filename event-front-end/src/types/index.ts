export interface IEventDetail {
    id: string;
    nomeEvento: string;
    dataEvento: string;
    ufEvento: string;
    localEvento: string;
    valorIngressoEvento: number;
    capacidadeEvento: string;
    descricaoEvento: string;
    banner?: string;
    avatar?: string;
}
export interface IEvent {
    idEvento: string;
    nomeEvento: string;
    dataEvento: string;
    estadoOrUFEvento: string;
    localEvento: string;
    banner?: string;
    avatar?: string;
}

export interface IUser {
    nome: string;
    email: string;
    role: 'USER' | 'ADMIN';
    token: string;
    senhaOriginal: string
    cpf: string;
}

export interface INotification {
    title: string;
    id: number;
    description: string;
}