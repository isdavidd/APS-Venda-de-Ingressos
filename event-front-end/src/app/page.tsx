import Image from 'next/image'
import Header from '../components/header';
export default function Home() {
  return (
    <main>
      <Header />
      <div className="relative w-full h-[40vh]">
        <Image
          className='blur-sm opacity-40'
          src="/images/gradient-banner.jpg"
          alt="Gradient Banner"
          layout="fill"
        />
      </div>
    </main>
  );
}
