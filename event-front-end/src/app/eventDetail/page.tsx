'use client';
import { useStore } from '@/src/app/store/useStore';
import { useEventDetail } from '../../hooks/useEventDetail';

export default function EventDetail() {
    const { event } = useStore();
    const { eventDetail } = useEventDetail({ id: event?.idEvento });
    return (
        <div className="max-w-2xl mx-auto p-6 bg-white shadow-lg rounded-2xl">
            <h1 className="text-3xl font-bold text-gray-800 mb-2">
                {eventDetail?.nomeEvento}
            </h1>
            <p className="text-gray-600">
                {eventDetail?.dataEvento} | {eventDetail?.localEvento} - {eventDetail?.ufEvento}
            </p>

            <div className="mt-4 border-t pt-4">
                <p className="text-gray-700">{eventDetail?.descricaoEvento}</p>
            </div>

            <div className="mt-6 p-4 bg-gray-100 rounded-xl flex justify-between items-center">
                <span className="text-xl font-semibold text-gray-900">
                    R$ {eventDetail?.valorIngressoEvento.toFixed(2)}
                </span>
                <button className="bg-blue-600 text-white px-6 py-2 rounded-xl hover:bg-blue-700">
                    Comprar Ingresso
                </button>
            </div>
        </div>
    );
}
