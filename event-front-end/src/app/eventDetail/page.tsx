'use client';
import { useStore } from '@/src/app/store/useStore';
import { useEventDetail } from '../../hooks/useEventDetail';
import { useRouter } from 'next/navigation';
import Image from 'next/image';

export default function EventDetail() {
    const { event } = useStore();
    const { eventDetail } = useEventDetail({ id: event?.idEvento });
    const router = useRouter();

    const handlePurchase = () => {
        router.push('/payment');
    };

    return (
        <div className="max-w-2xl mx-auto p-6 bg-white shadow-lg rounded-2xl">
            <div className="flex flex-col items-center">
                    <Image
                        src="/images/concert-banner.png"
                        alt="Concert Banner"
                        width={500}
                        height={256}
                        className="w-full h-64 object-cover rounded-t-2xl"
                    />
                <h1 className="text-3xl font-bold text-gray-800 mt-4 mb-2">
                    {eventDetail?.nomeEvento}
                </h1>
                <p className="text-gray-600">
                    {eventDetail?.dataEvento} | {eventDetail?.localEvento} -{' '}
                    {eventDetail?.ufEvento}
                </p>
            </div>

            <div className="mt-4 border-t pt-4">
                <p className="text-gray-700">{eventDetail?.descricaoEvento}</p>
            </div>

            <div className="mt-6 p-4 bg-gray-100 rounded-xl flex justify-between items-center">
                <span className="text-xl font-semibold text-gray-900">
                    R$ {eventDetail?.valorIngressoEvento.toFixed(2)}
                </span>
                <button
                    onClick={handlePurchase}
                    className="bg-blue-600 text-white px-6 py-2 rounded-xl hover:bg-blue-700"
                >
                    Comprar Ingresso
                </button>
            </div>
        </div>
    );
}
