import React from "react";

interface Props {
    message?: string
}

const SuccessBanner: React.FC<Props> = ({message}) => {

    return (
        <div style={{color: 'blue'}}>
            {message && <p>{message}</p>}
        </div>
    );
}

export default SuccessBanner