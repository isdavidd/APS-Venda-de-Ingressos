import { create } from 'zustand';
import { IEvent, IUser } from '../../types';

interface State {
    isOpenModalNotifications: boolean;
    event: IEvent | undefined;
    update: <K>(key: string, value: K) => void;
    userData: IUser | undefined;
}

const loadStateFromLocalStorage = () => {
    const savedState = localStorage.getItem('zustandState');
    return savedState ? JSON.parse(savedState) : {};
};

export const useStore = create<State>((set) => {
    const initialState = loadStateFromLocalStorage();

    return {
        isOpenModalNotifications:
            initialState.isOpenModalNotifications || false,
        event: initialState.event || null,
        userData: initialState.userData || undefined,

        update: (key, value) =>
            set((state) => {
                const newState = { ...state, [key]: value };
                localStorage.setItem('zustandState', JSON.stringify(newState));
                return newState;
            }),
    };
});
