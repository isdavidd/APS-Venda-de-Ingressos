import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState } from 'react';
import { useStore } from '../../store/useStore';

const SearchBar = () => {
    const { userData, update } = useStore();
    const [searchTerm, setSearchTerm] = useState('');

    const fetchEvents = async () => {
        if (!searchTerm.trim()) return; // Evita buscas vazias
        const headers = new Headers();
        headers.set('Content-Type', 'application/json');

        try {
            try {
                const authToken = userData?.token ?? '';
                headers.set('Authorization', authToken);
            } catch (error) {
                console.error('Erro ao buscar token de autenticação:', error);
                throw error;
            }

            const response = await fetch(
                `http://localhost:8080/event-management/event/buscar?nomeEvento=${encodeURIComponent(
                    searchTerm,
                )}`,
                {
                    method: 'GET',
                    headers: headers,
                },
            );

            if (!response.ok) {
                throw new Error('Erro ao buscar eventos');
            }

            const data = await response.json();
            console.log(data);
            update('events', data);
        } catch (error) {
            console.error('Erro ao buscar eventos:', error);
        }
    };

    return (
        <div className="flex h-[40px] w-[30vw] gap-3">
            <input
                type="text"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                placeholder="Pesquisar shows, eventos, teatros..."
                className="border border-gray-300 outline-none rounded-lg h-full w-full p-4 placeholder-black"
            />
            <button
                onClick={fetchEvents}
                className="flex bg-gray-900 p-2 rounded-full hover:bg-gray-800 h-10 w-11 justify-center items-center"
            >
                <FontAwesomeIcon
                    icon={faSearch}
                    className="text-white h-5 w-5"
                />
            </button>
        </div>
    );
};

export default SearchBar;
