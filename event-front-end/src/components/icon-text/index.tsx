import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconDefinition } from '@fortawesome/fontawesome-common-types';
import { FC } from 'react';
import Link from 'next/link';

interface IconTextProps {
    icon: IconDefinition;
    text: string;
    onClick?: () => void;
}

const IconText: FC<IconTextProps> = ({ icon, text, onClick }) => {
    return (
        <button className='flex gap-2 px-3' onClick={onClick}>
            <FontAwesomeIcon icon={icon} className='h-6 w-6' />
            <span>{text}</span>
        </button>
    );
};

export default IconText