'use client';
import { useStore } from '@/src/app/store/useStore';
import { useEventDetail } from '../../hooks/useEventDetail';
import { formatDate } from '@/src/utils/format';

export default function EventDetail() {
    const { event } = useStore();
    const { eventDetail } = useEventDetail({ id: event?.idEvento });
    return (
        <main>
            <div>
                <h1 className="text-3xl p-5 font-bold">Event detail</h1>
                <h1 className="text-3xl p-5 font-bold">
                    {eventDetail?.nomeEvento}
                    {eventDetail?.dataEvento
                        ? formatDate(eventDetail?.dataEvento)
                        : eventDetail?.dataEvento}
                </h1>
            </div>
        </main>
    );
}
