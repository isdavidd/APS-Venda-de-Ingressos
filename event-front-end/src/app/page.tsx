'use client';
import Image from 'next/image';
import SearchBar from './components/search-bar';
import ListEvents from './components/list-events';

export default function Home() {
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

            <ListEvents />
        </main>
    );
}
