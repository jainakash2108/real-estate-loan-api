import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Header from './app/components/Header';
import AdminRegistrationPage from './app/pages/AdminRegistrationPage';
import AdviserViewPage from './app/pages/AdviserViewPage';
import CustomerViewPage from './app/pages/CustomerViewPage';
import HomePage from './app/pages/HomePage';
import UserRegistrationPage from './app/pages/UserRegistrationPage';

const App: React.FC = () => {
    return (
        <div>
            <Header/>
            <BrowserRouter basename='/loan-api'>
                <Routes>
                    <Route path='/' element={<HomePage/>}/>
                    <Route path='/home/page' element={<HomePage/>}/>
                    <Route path='/user/register' element={<UserRegistrationPage/>}/>
                    <Route path='/admin/register' element={<AdminRegistrationPage/>}/>
                    <Route path='/customer/page' element={<CustomerViewPage/>}/>
                    <Route path='/adviser/page' element={<AdviserViewPage/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default App
