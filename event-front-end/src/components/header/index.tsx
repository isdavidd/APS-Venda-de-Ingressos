import { faBell, faPlusSquare, faUser } from '@fortawesome/free-regular-svg-icons'
import React from 'react'
import IconText from '../icon-text'

const Header = () => {
    return (
        <div className='bg-white p-4 z-10 flex'>
            <h1 className='text-3xl font-extrabold'>Ingressos</h1>
            <div className='flex items-center justify-end w-full'>
                <IconText text='Crie seu evento' icon={faPlusSquare} to='/create-event' />
                <IconText text='Notificações' icon={faBell} to='/event' />
                <IconText text='Olá, visitante' icon={faUser} to='/event' />
            </div>
        </div>
    )
}

export default Header
