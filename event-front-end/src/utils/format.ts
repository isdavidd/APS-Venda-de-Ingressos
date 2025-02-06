import { format } from 'date-fns';
import { ptBR } from 'date-fns/locale';

export const formatDate = (isoString: string | undefined) => {
    if (!isoString) {
        return null;
    }
    return format(new Date(isoString), 'dd/MM/yyyy - HH:mm', {
        locale: ptBR,
    });
};
