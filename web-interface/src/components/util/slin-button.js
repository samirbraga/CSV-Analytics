import React from 'react';
import { Link } from 'react-router-dom';
import cx from 'classnames';

import './slin-button.css';

const SlinButton = props => (
    <Link {...props} className={cx("slin-button", props.className)}  >
        {props.children}
    </Link>
);

export default SlinButton;