import React from 'react'

export interface Notification {
    title: string;
    id: number;
    description: string;
};

interface NotificationItemProps {
    item: Notification;
};

const NotificationItem = ({ item }: NotificationItemProps) => {
    return (
        <div key={item.id} className='border rounded-lg shadow-md shadow-gray-350'>
            <h3 className='text-sm'>{item.title}</h3>
            <p className='text-sm'>{item.description}</p>
        </div>
    )
};

export default NotificationItem