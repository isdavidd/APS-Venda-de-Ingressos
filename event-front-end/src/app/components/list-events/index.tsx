'use client';

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { fetchFromAPI } from '@/src/services/api';
import { IEvent } from '@/src/types';
import EventItem from '../event-item';
import { useStore } from '@/src/app/store/useStore';
import Loading from '../loading-component';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faExclamationTriangle } from '@fortawesome/free-solid-svg-icons';

export default function ListEvents() {
    const router = useRouter();
    const { update, events, isLoadingList, isErrorList } = useStore();

    useEffect(() => {
        async function getEvents() {
            update('isLoadingList', true);
            update('isErrorList', null);
            try {
                const data = await fetchFromAPI<IEvent[]>('/events');
                console.log("list-events", data);
                update('events', data);
            } catch (err) {
                update('isErrorList', (err as Error).message);
                update('events', undefined);
            } finally {
                update('isLoadingList', false);
            }
        }

        getEvents();
    }, []);

    if (isLoadingList) {
        return (
            <div className="p-24">
                <Loading />
            </div>
        );
    }

    if (isErrorList) {
        return (
            <div className="flex items-center justify-center space-x-2 p-24">
                <FontAwesomeIcon
                    icon={faExclamationTriangle}
                    className="text-red-500"
                    size="lg"
                />
                <p>Erro ao carregar eventos!</p>
            </div>
        );
    }
    if (!isErrorList && !isLoadingList && !events) {
        return (
            <div className="flex items-center justify-center space-x-2 p-24">
                <p>Nenhum evento encontrado!</p>
            </div>
        );
    }

    return (
        <main>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 py-16 px-40">
                {events?.map((item: IEvent) => (
                    <div
                        className="cursor-pointer"
                        key={item?.idEvento}
                        onClick={() => {
                            update('event', item);
                            router.push('/eventDetail');
                        }}
                    >
                        <EventItem {...item} />
                    </div>
                ))}
            </div>
        </main>
    );
}
