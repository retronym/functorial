package functorial

trait FunctorState[F[+_],S] extends Functor[F] {
  def get: F[S] = state[S](s => (s,s))
  def gets[T](f: S => T): F[T] = state[T](s => (f(s),s))
  def modify(f: S => S): F[S] = state[S](s => { val s2 = f(s); (s2,s2) })
  def put(s: S): F[S] = state[S](_ => (s, s))
  def state[A](f: S => (A,S)): F[A]
}

