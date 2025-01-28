import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';

const SearchBar = () => {
    return (
        <div className="flex h-[40px] w-[30vw] gap-3">
            <input
                type="text"
                placeholder="Pesquisar shows, eventos, teatros..."
                className="border border-gray-300 outline-none rounded-lg h-full w-full p-4 placeholder-black"
            />
            <button className="flex bg-gray-900 p-2 rounded-full hover:bg-gray-800 h-10 w-11 justify-center items-center">
                <FontAwesomeIcon icon={faSearch} className="text-white h-5 w-5" />
            </button>
        </div>
    );
};

export default SearchBar;
