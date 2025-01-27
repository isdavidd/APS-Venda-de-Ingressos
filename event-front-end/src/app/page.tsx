import Image from 'next/image'
import EventItem from '../components/event-item';

export default function Home() {
  return (
    <main>
      <div className="relative w-full h-[40vh]">
        <Image
          className='blur-sm opacity-40'
          src="/images/gradient-banner.jpg"
          alt="Gradient Banner"
          layout="fill"
        />
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
