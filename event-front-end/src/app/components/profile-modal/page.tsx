'use client';

import { useStore } from "../../store/useStore";
import { LogOut, User } from "lucide-react";
import { useState } from "react";

export default function UserModal() {
  const [isOpen, setIsOpen] = useState(false);
  const { userData, update } = useStore();

  if (!userData) return null; // Não exibe o modal se não houver usuário logado

  const handleLogout = () => {
    localStorage.removeItem('zustandState'); // Limpa o localStorage
    update('userData', undefined); // Atualiza o estado global
    setIsOpen(false); // Fecha o modal
  };

  return (
    <div className="relative">
      {/* Botão para abrir o modal */}
      <button 
        onClick={() => setIsOpen(!isOpen)} 
        className="p-2 rounded-full bg-gray-200 hover:bg-gray-300 transition"
      >
        <User className="w-6 h-6 text-gray-700" />
      </button>

      {/* Modal */}
      {isOpen && (
        <div 
          className="absolute right-0 mt-2 w-48 bg-white shadow-lg rounded-lg p-2 border border-gray-200"
        >
          <ul>
            <li className="p-2 hover:bg-gray-100 rounded-md cursor-pointer">
              Perfil e Ingressos
            </li>
            <li 
              className="p-2 hover:bg-gray-100 rounded-md cursor-pointer flex items-center gap-2 text-red-600"
              onClick={handleLogout} // Chama a função de logout
            >
              <LogOut className="w-5 h-5" /> Sair
            </li>
          </ul>
        </div>
      )}
    </div>
  );
}
