import { FunctionComponent } from "react";
import { App } from "../App";
import { ConferenceComponent } from "./Conferences/components/Conference";
import UpdateConf from "./Conferences/UpdateConf";

const StaffApp: FunctionComponent = () => {
  return (
    <App>
      <div>
        Edita <code>src/app/components/Staff.tsx</code> para continuar
      </div>
      <ConferenceComponent />
      <UpdateConf />
    </App>
  );
};

export default StaffApp;
