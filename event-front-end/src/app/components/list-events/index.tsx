'use client';
import { useRouter } from 'next/navigation';
import { useEvents } from '../../../hooks/useEvents';
import { IEvent } from '../../../types';
import EventItem from '../event-item';
import { useStore } from '@/src/app/store/useStore';
import Loading from '../loading-component';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faExclamationTriangle } from '@fortawesome/free-solid-svg-icons';

export default function ListEvents() {
    const router = useRouter();
    const { events, loading, error } = useEvents();
    const { update } = useStore();

    if (loading) {
        return (
            <div className="p-24">
                <Loading />
            </div>
        );
    }
    if (error)
        return (
            <div className="flex items-center justify-center space-x-2 p-24">
                <FontAwesomeIcon
                    icon={faExclamationTriangle}
                    className="text-red-500"
                    size="lg"
                />
                <p>
                    Erro ao carregar eventos: {error}
                </p>
            </div>
        );

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
