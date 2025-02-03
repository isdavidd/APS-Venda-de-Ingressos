'use client';
import { useState, useEffect } from 'react';
import { fetchFromAPI } from '../services/api';
import { IEvent } from '../types';

export function useEvents() {
    const [events, setEvents] = useState<IEvent[] | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        async function getEvents() {
            try {
                const data = await fetchFromAPI<IEvent[]>('/events');
                setEvents(data);
            } catch (err) {
                setError((err as Error).message);
            } finally {
                setLoading(false);
            }
        }

        getEvents();
    }, []);

    return { events, loading, error };
}
