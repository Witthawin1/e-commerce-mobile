import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    specPattern: 'cypress/e2e/**/*.{cy,spec}.{js,jsx,ts,tsx}',
    experimentalRunAllSpecs: true,
    baseUrl: 'http://localhost:5173',
    baseAPI: 'http://localhost:8080/itb-mshop',
    // baseUrl: 'http://intproj24.sit.kmutt.ac.th/ssa3/',
    // baseAPI: 'http://intproj24.sit.kmutt.ac.th:8080/ssa3/itb-mshop/',
    // baseUrl: 'http://intproj24.sit.kmutt.ac.th/ssa3/',
    // baseAPI: 'http://intproj24.sit.kmutt.ac.th:8080/ssa3/itb-mshop/',
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
