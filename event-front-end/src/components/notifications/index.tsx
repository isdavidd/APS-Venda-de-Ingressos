import React from 'react';
import NotificationItem, { Notification } from '../notification-item';
import { faClose } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface NotificationModalProps {
    listNotifications: Notification[];
    onClickClose: () => void;
}

const NotificationModal = ({
    listNotifications,
    onClickClose,
}: NotificationModalProps) => {
    return (
        <div className="fixed inset-0 flex items-start justify-end bg-black bg-opacity-50 z-50 mt-[4.2rem]">
            <div className="bg-white rounded-b-2xl shadow-lg p-6 max-w-lg w-full transform transition-all duration-300 scale-100">
                <FontAwesomeIcon
                    aria-label="Close"
                    onClick={onClickClose}
                    icon={faClose}
                    className="absolute top-3 right-3 text-gray-500 hover:text-gray-700 transition duration-200 h-6 w-6"
                />
                <div className="flex flex-col gap-2 pt-4">
                    {listNotifications.map((notification) => (
                        <NotificationItem
                            key={notification.id}
                            item={notification}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default NotificationModal;
