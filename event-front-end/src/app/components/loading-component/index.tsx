import React from 'react';

const Loading = () => {
    return (
        <div className="flex items-center justify-center space-x-2">
            <div className="w-4 h-4 border-4 border-blue-500 border-t-transparent border-solid rounded-full animate-spin"></div>
            <p>Carregando eventos...</p>
        </div>
    );
};

export default Loading;