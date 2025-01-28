'use client'
import React, { useState } from "react";
import { faBell, faPlusSquare, faUser } from '@fortawesome/free-regular-svg-icons'
import IconText from '../icon-text'
import { useRouter } from "next/navigation";
import NotificationModal from "../notifications";
import { Notification } from "../notification-item";




const Header = () => {
    const router = useRouter();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const mockNotifications: Array<Notification> =
        [
            { id: 1, title: 'Evento 1', description: 'Evento 1' },
            { id: 2, title: 'Evento 2', description: 'Evento 2' },
        ]
    return (
        <div className='bg-white p-4 z-10 flex'>
            <h1 className='text-3xl font-extrabold'>Ingressos</h1>
            <div className='flex items-center justify-end w-full'>
                <IconText text='Crie seu evento' icon={faPlusSquare} onClick={() => router.push('/create-event')} />
                <IconText text='Notificações' icon={faBell} onClick={() => setIsModalOpen(!isModalOpen)} />
                <IconText text='Olá, visitante' icon={faUser} onClick={() => router.push('/user')} />
            </div>
            {isModalOpen && <NotificationModal listNotifications={mockNotifications} />}
        </div>
    )
}

export default Header
