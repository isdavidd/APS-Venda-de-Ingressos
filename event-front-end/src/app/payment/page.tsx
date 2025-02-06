'use client';
import { useState } from 'react';
import EventItem from '../components/event-item';

export default function PaymentScreen() {
    const [paymentMethod, setPaymentMethod] = useState('pix');
    const eventoExemplo = {
        idEvento: '12345',
        nomeEvento: 'Concerto de Rock',
        dataEvento: '2023-12-31',
        estadoOrUFEvento: 'SP',
        localEvento: 'Estádio do Morumbi',
        banner: 'https://example.com/banner.jpg',
        avatar: 'https://example.com/avatar.jpg',
    };
    return (
        <div className="flex justify-center items-center min-h-screen bg-gray-100 p-4">
            <div className="w-full max-w-md p-6 bg-white shadow-lg rounded-2xl">
                <div>
                    <h2 className="text-center text-xl font-bold">Pagamento</h2>
                </div>
                <div className="pt-8 pb-8">
                    <EventItem {...eventoExemplo} />
                </div>
                <div>
                    <div className="mb-4">
                        <label className="block text-sm font-medium text-gray-700 mb-2">
                            Escolha o método de pagamento:
                        </label>
                        <div className="flex gap-4">
                            <button
                                className={`px-4 py-2 rounded-lg border ${
                                    paymentMethod === 'pix'
                                        ? 'bg-blue-500 text-white'
                                        : 'bg-gray-200'
                                }`}
                                onClick={() => setPaymentMethod('pix')}
                            >
                                PIX
                            </button>
                            <button
                                className={`px-4 py-2 rounded-lg border ${
                                    paymentMethod === 'boleto'
                                        ? 'bg-blue-500 text-white'
                                        : 'bg-gray-200'
                                }`}
                                onClick={() => setPaymentMethod('boleto')}
                            >
                                Boleto
                            </button>
                        </div>
                    </div>
                    <button className="w-full bg-green-500 hover:bg-green-600 text-white py-2 rounded-lg mt-4">
                        Confirmar Compra
                    </button>
                </div>
            </div>
        </div>
    );
}
