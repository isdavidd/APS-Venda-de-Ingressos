'use client';
import { useRouter } from 'next/navigation';
import { useEvents } from '../../../hooks/useEvents';
import { IEvent } from '../../../types';
import EventItem from '../event-item';
import { useStore } from '@/src/app/store/useStore';

export default function ListEvents() {
    const router = useRouter();
    const { events, loading, error } = useEvents();
    const { update } = useStore();
    console.log(events);

    if (loading) return <p>Carregando eventos...</p>;
    if (error) return <p>Erro ao carregar eventos: {error}</p>;

    return (
        <main>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 py-16 px-40">
                {events?.map((item: IEvent) => (
                    <div
                        className="cursor-pointer"
                        key={item.idEvento}
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
