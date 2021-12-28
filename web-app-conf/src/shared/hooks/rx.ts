import { useEffect, useState } from 'react'
import { BehaviorSubject, Observable } from 'rxjs'

export const useObservableSubscription = <T>(
  observable: Observable<T>
): T | undefined => {
  const [value, setValue] = useState<T | undefined>(
    observable instanceof BehaviorSubject ? observable.getValue() : undefined
  )

  useEffect(() => {
    const sub = observable.subscribe(setValue)
    return () => sub.unsubscribe()
  }, [observable])

  return value
}
