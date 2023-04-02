import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

test('Real Estate Loan application', () => {
  render(<App />);
  const linkElement = screen.getByText(/Real Estate Loan application/i);
  expect(linkElement).toBeInTheDocument();
});
