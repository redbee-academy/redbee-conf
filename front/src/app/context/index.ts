import { createContext } from "react";
import { AppConfiguration } from "../domain";

export const AppConfigurationContext = createContext<AppConfiguration | undefined>(undefined)