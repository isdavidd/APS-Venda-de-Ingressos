import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconDefinition } from '@fortawesome/fontawesome-common-types';
import { FC } from 'react';

interface IconTextProps {
    icon: IconDefinition;
    text: string;
    onClick?: () => void;
    className?: string;
}

const IconText: FC<IconTextProps> = ({ icon, text, onClick, className }) => {
    return (
        <button className={`flex items-center gap-2 px-3 py-2 rounded-md transition-colors ${className || ''}`}
                onClick={onClick}>
            <FontAwesomeIcon icon={icon} className='h-6 w-6' />
            <span>{text}</span>
        </button>
    );
};

export default IconText