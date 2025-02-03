export interface IEventDetail {
    id: string;
    nomeEvento: string;
    dataEvento: string;
    ufEvento: string;
    localEvento: string;
    valorIngressoEvento: string;
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
}
