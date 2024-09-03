package com.field.Enums;


/**
 * Enum representing different wait strategies to be used when interacting with web elements.
 * These strategies define how the WebDriver should wait for an element before performing actions on it.
 *
 *
 *  * @author Ezhilraj
 *  * @version 1.0
 *  * @since 1.0
 */
public enum WaitStrategy {

    /**
     * Waits for the presence of an element in the DOM.
     * This strategy does not guarantee that the element is visible or clickable,
     * only that it is present in the DOM.
     */
    PRESENCE,

    /**
     * Waits for an element to be visible on the page.
     * This strategy ensures that the element is both present in the DOM and visible to the user.
     */
    VISIBLE,

    /**
     * Waits for an element to be clickable.
     * This strategy ensures that the element is present, visible, and enabled, allowing for click actions.
     */
    CLICKABLE,

    /**
     * No waiting strategy is applied.
     * This option is used when no explicit wait is needed before interacting with an element.
     */
    NONE;
}


