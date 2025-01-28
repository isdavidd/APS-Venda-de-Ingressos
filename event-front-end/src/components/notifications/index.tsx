import React from 'react'
import NotificationItem, { Notification } from '../notification-item';

interface NotificationModalProps {
  listNotifications: Notification[];
}


const NotificationModal = ({ listNotifications }: NotificationModalProps) => {
  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
  <div className="bg-white rounded-2xl shadow-lg p-6 max-w-lg w-full transform transition-all duration-300 scale-100">
    <button 
      className="absolute top-3 right-3 text-gray-500 hover:text-gray-700 transition duration-200"
      aria-label="Close"
    >
      &times;
    </button>
    {listNotifications.map((notification) => (
        <NotificationItem item={notification} />))}
    
  </div>
</div>



   /* <div className="bg-white rounded-2xl shadow-lg p-6 max-w-lg w-full transform transition-all duration-300 scale-100">
      {listNotifications.map((notification) => (
        <NotificationItem item={notification} />))}
    </div>
    */
  )
}

export default NotificationModal