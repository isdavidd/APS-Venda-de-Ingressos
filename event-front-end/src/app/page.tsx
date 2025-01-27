import Image from 'next/image'

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
    </main>
  );
}
