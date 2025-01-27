import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconDefinition } from '@fortawesome/fontawesome-common-types';
import { FC } from 'react';
import Link from 'next/link';

interface IconTextProps {
    icon: IconDefinition;
    text: string;
    to?: string;
}

const IconText: FC<IconTextProps> = ({ icon, text, to = '/' }) => {
    return (
        <Link href={to}>
            <div className='flex gap-2 px-3'>
                <FontAwesomeIcon icon={icon} className='h-6 w-6' />
                <span>{text}</span>
            </div>
        </Link>
    );
};

export default IconText