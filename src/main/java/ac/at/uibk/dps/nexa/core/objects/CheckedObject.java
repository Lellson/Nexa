package ac.at.uibk.dps.nexa.core.objects;

import ac.at.uibk.dps.nexa.lang.checker.CheckerException;

public interface CheckedObject {

  CheckedObject validate() throws CheckerException;
}
