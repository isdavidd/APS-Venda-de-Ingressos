import Image from 'next/image'
import EventItem from '../components/event-item';
import SearchBar from '../components/search-bar';

export default function Home() {
  return (
    <main>
      <div className="relative w-full h-[40vh]">
        <Image
          className='blur-sm opacity-30'
          src="/images/gradient-banner.jpg"
          alt="Gradient Banner"
          layout="fill"
        />
        <div className="absolute top-1/2 left-1/4 transform -translate-x-1/2 -translate-y-1/2">
          <SearchBar />
        </div>
      </div>

      <div className='grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 py-16 px-40'>
        <EventItem
          avatar='/images/avatar.png'
          banner='/images/concert-banner.png'
          title="Festa Maneira"
          local="Longe - Acre"
          date='27 jan. de 2024'
        />
        <EventItem
          avatar='/images/avatar.png'
          banner='/images/concert-banner.png'
          title="Festa Maneira"
          local="Longe - Acre"
          date='27 jan. de 2024'
        />
        <EventItem
          avatar='/images/avatar.png'
          banner='/images/concert-banner.png'
          title="Festa Maneira"
          local="Longe - Acre"
          date='27 jan. de 2024'
        />
        <EventItem
          avatar='/images/avatar.png'
          banner='/images/concert-banner.png'
          title="Festa Maneira"
          local="Longe - Acre"
          date='27 jan. de 2024'
        />
        <EventItem
          avatar='/images/avatar.png'
          banner='/images/concert-banner.png'
          title="Festa Maneira"
          local="Longe - Acre"
          date='27 jan. de 2024'
        />
        <EventItem
          avatar='/images/avatar.png'
          banner='/images/concert-banner.png'
          title="Festa Maneira"
          local="Longe - Acre"
          date='27 jan. de 2024'
        />
      </div>
    </main>
  );
}
