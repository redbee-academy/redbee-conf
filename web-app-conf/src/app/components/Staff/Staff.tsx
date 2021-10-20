import { FunctionComponent } from "react";
import { App } from "../App";
import UpdateConf from "./Conferences/UpdateConf";

const StaffApp: FunctionComponent = () => {
  return <App><div>Edita <code>src/app/components/Staff.tsx</code> para continuar</div>
  <UpdateConf confId='1'/>
  </App>
}

export default StaffApp