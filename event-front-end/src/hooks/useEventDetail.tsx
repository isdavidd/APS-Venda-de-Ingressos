'use client';
import { useState, useEffect } from 'react';
import { fetchFromAPI } from '../services/api';
import { IEventDetail } from '../types';

interface EventDetailProps {
    id: string | undefined;
}

export function useEventDetail({ id }: EventDetailProps) {
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [eventDetail, setEventDetail] = useState<IEventDetail | null>(null);

    useEffect(() => {
        async function getEventDetail() {
            try {
                const data = await fetchFromAPI<IEventDetail>(
                    `/event/id-evento/${id}`,
                );
                setEventDetail(data);
            } catch (err) {
                setError((err as Error).message);
            } finally {
                setLoading(false);
            }
        }

        getEventDetail();
    }, [id]);

    return { eventDetail, loading, error };
}
