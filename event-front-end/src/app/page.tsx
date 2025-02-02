'use client';
import Image from 'next/image';
import EventItem from './components/event-item';
import SearchBar from './components/search-bar';
import { useEvents } from './hooks/useEvents';
import { IEvent } from './types';

export default function Home() {
    const { events, loading, error } = useEvents();
    console.log(events);

    if (loading) return <p>Carregando eventos...</p>;
    if (error) return <p>Erro ao carregar eventos: {error}</p>;

    return (
        <main>
            <div className="relative w-full h-[40vh]">
                <Image
                    className="blur-sm opacity-30"
                    src="/images/gradient-banner.jpg"
                    alt="Gradient Banner"
                    layout="fill"
                />
                <div className="absolute top-1/2 left-1/4 transform -translate-x-1/2 -translate-y-1/2">
                    <SearchBar />
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 py-16 px-40">
                {events?.map((item: IEvent) => (
                    <div key={item.id}>
                        <EventItem {...item} />
                    </div>
                ))}
            </div>
        </main>
    );
}
