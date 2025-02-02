import { faCircleInfo, faClose } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';

export interface Notification {
    title: string;
    id: number;
    description: string;
}

interface NotificationItemProps {
    item: Notification;
}

const NotificationItem = ({ item }: NotificationItemProps) => {
    return (
        <div
            key={item.id}
            className="shadow-md flex flex-row items-start gap-4 p-3 justify-between"
        >
            <FontAwesomeIcon icon={faCircleInfo} className="h-6 w-6" />
            <div className="flex flex-col gap-1 w-full">
                <h3 className="text-md font-bold">{item.title}</h3>
                <p className="text-sm">{item.description}</p>
            </div>
            <FontAwesomeIcon
                aria-label="Close"
                onClick={() => console.log(`Apagar ${item.id}`)}
                icon={faClose}
                className="text-red-950 hover:text-red-900 h-6 w-6 self-center"
            />
        </div>
    );
};

export default NotificationItem;
