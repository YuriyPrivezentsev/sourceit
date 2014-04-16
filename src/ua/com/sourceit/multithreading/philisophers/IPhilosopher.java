package ua.com.sourceit.multithreading.philisophers;

/**
 * Интерфейс философа. Каждый философ запускает отдельный поток. В этом потоке филосов выполняет одно из двух действий:
 * либо спит, либо забрав правую и левую вилку (войдя в секцию synchronized по каждому объекту) ест
 * (на самом деле поток таж же уходит в sleep). Если философ не может поесть в течении времени в два раза большего,
 * чем время за которое он проголодался, он умирает. Время еды и сна - параметры
 * задаваемые при создании класса. Задача создать философа таким образом, чтобы, учитывая что его правая вилка является
 * для другого философа левой, не возникало проблем класса DeadLock, LiveLock или starvation.
 *
 */
public interface IPhilosopher {
    /**
     * Определение объекта правой вилки.
     * @param rightFork
     */
    void setRightFork(IFork rightFork);

    /**
     * Определение объекта левой вилки.
     * @param leftFork
     */
    void setLeftFork(IFork leftFork);

    /**
     * Установка времени еды.
     * @param milliseconds
     */
    void setEatingTime(int milliseconds);

    /**
     * Установка времени голодания.
     * @param milliseconds
     */
    void setGettingHungryTime(int milliseconds);

    /**
     * Установить имя философа
     * @param name
     */
    void setName(String name);

    /**
     * Запуск выполнения действий филосова. Этот метод должен запускать отдельный поток.
     * @throws IllegalStateException - если какой-либо параметр не был установлен.
     *
     * IllegalStateException расширяет RuntimeException, поэтому декларировать его в принципе неправильно,
     * но т.к. единственной спецификацией у нас является интерфейс, то приходится так поступать.
     */
    void start() throws IllegalStateException;
}
