import React from 'react';
import Image from 'next/image';
import { IEvent } from '../../../types';
import { formatDate } from '@/src/utils/format';

const EventItem: React.FC<IEvent> = (itemEvent) => {
    const { nomeEvento, localEvento, dataEvento, estadoOrUFEvento } = itemEvent;
    return (
        <div className="border border-gray-200 rounded-lg shadow-md shadow-gray-350">
            <div className="flex items-center gap-3 p-3">
                <Image
                    src="/images/avatar.png"
                    alt="Avatar"
                    height={30}
                    width={30}
                />
                <span className="font-semibold text-xs">Eventos e Cia</span>
            </div>

            <div className="relative text-sm w-full h-48">
                <Image
                    src="/images/concert-banner.png"
                    alt="Concert Banner"
                    layout="fill"
                />
            </div>

            <div className="p-4 flex flex-col">
                <h1 className="text-sm">{nomeEvento}</h1>
                <span className="text-xs text-gray-600">
                    {localEvento} - {estadoOrUFEvento}
                </span>
                <span className="pt-8 text-xs text-gray-800">
                    {formatDate(dataEvento)}
                </span>
            </div>
        </div>
    );
};

export default EventItem;
