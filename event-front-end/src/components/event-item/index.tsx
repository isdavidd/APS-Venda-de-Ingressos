import React, { FC } from 'react'
import Image from 'next/image'

interface EventItemProps {
    avatar: string;
    banner: string;
    title: string;
    local: string;
    date: string;
}

const EventItem: FC<EventItemProps> = ({ avatar, banner, title, local, date }) => {
    return (
        <div className='border border-gray-200 rounded-lg shadow-md shadow-gray-350'>
            <div className='flex items-center gap-3 p-3'>
                <Image
                    src={avatar}
                    alt="Avatar"
                    height={30}
                    width={30}
                />
                <span className='font-semibold text-xs'>Eventos e Cia</span>
            </div>

            <div className='relative text-sm w-full h-48'>
                <Image
                    src={banner}
                    alt="Concert Banner"
                    layout="fill"
                />
            </div>

            <div className='p-4 flex flex-col'>
                <h1 className='text-sm'>{title}</h1>
                <span className='text-xs text-gray-600'>{local}</span>
                <span className='pt-8 text-xs text-gray-800'>{date}</span>
            </div>
        </div>
    )
}

export default EventItem