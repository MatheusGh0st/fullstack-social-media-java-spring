/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./public/index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {},
  },
  fontFamily: {
    Roboto: ["Roboto, sans-serif"],
  },
  conatiner: {
    padding: "2rem",
    center: true,
  },
  screens: {
    sm: "640px",
    md: "768px",
  },
  plugins: [],
}

