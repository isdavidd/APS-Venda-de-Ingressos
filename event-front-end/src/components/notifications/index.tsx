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
    const handleBackdropClick = (event: React.MouseEvent<HTMLDivElement>) => {
        onClickClose();
    };

    const handleModalClick = (event: React.MouseEvent<HTMLDivElement>) => {
        event.stopPropagation();
    };

    return (
        <div
            className="fixed inset-0 flex items-start justify-end bg-black bg-opacity-50 z-50 mt-[3.8rem]"
            onClick={handleBackdropClick}
        >
            <div
                className="bg-white rounded-b-2xl shadow-lg p-6 max-w-lg w-full transform transition-all duration-300 scale-100"
                onClick={handleModalClick} // Bloqueia a propagação para o fundo
            >
                <div className="flex flex-col gap-2 pt-4 ">
                    {listNotifications.length === 0 ? (
                        <p className="text-gray-500 flex justify-center">Nenhuma notificação disponível.</p>
                    ) : (
                        listNotifications.map((notification) => (
                            <NotificationItem
                                key={notification.id}
                                item={notification}
                            />
                        ))
                    )}
                </div>
            </div>
        </div>
    );
};

export default NotificationModal;
