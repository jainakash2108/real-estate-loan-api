import React from "react";

interface Props {
    message: string
}

const ErrorBanner: React.FC<Props> = ({message}) => {

    return (
        <div style={{color: 'red'}}>
            {message && message}
        </div>
    );
}

export default ErrorBanner