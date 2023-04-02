import React from "react";

export const validatePassword = (rule: any, value: string, callback: any) => {
    if (
        !value.match(
            /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.{8,12})/
        )
    ) {
        callback(
            <div>
                <ul>
                    <li>{'Password must be 8-12 characters long'}</li>
                    <li>{'At least one uppercase letter'}</li>
                    <li>{'At least one lowercase letter'}</li>
                    <li>{'At least one special character'}</li>
                </ul>
            </div>
        );
    } else {
        callback();
    }
};


export const validateEmail = (email: string): boolean => {
    // basic email validation regex
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
};


export const validateCustomerSSN = (_: any, value: string) => {
    if (!/^\d{0,11}$/.test(value)) {
        return Promise.reject('Customer SSN should be 11 digits long');
    }
    return Promise.resolve();
};

export const validateAmount = (_: any, value: string) => {
    const numericValue = parseFloat(value);
    if (numericValue < 10000 || numericValue > 1000000000) {
        return Promise.reject('Amount must be between 10000 and 1000000000.');
    }
    return Promise.resolve();
};